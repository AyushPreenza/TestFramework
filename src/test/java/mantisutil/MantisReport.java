package mantisutil;

import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.MCException;
import org.mantisbt.connect.model.IProject;
import org.mantisbt.connect.model.Issue;
import org.mantisbt.connect.model.MCAttribute;
import utilities.GlobalUtil;
import utilities.LogUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The type Mantis report.
 */
public class MantisReport {

	/**
	 * Report issue string.
	 *
	 * @param summary               the summary
	 * @param description           the description
	 * @param category              the category
	 * @param additionalInformation the additional information
	 * @param filename              the filename
	 * @return the string
	 */
	public static String reportIssue(String summary, String description, String category, String additionalInformation,
									 String filename) {

		if (GlobalUtil.getCommonSettings().getbugTool().equalsIgnoreCase("No")) {
			return "No Bug tracking tool configured";
		}

		IMCSession sessions;
		String bugID = null;
		try {

			// getting the mantis connection
			sessions = ConnectMantis.getSession();

			// creating the issue for the project with the details mentioned
			IProject project = sessions.getProject(ConnectMantis.MANTIS_PROJET);
			Issue issue = new Issue();

			issue.setProject(new MCAttribute(project.getId(), project.getName()));
			issue.setAdditionalInformation(null);
			if (GlobalUtil.getCommonSettings().getExecutionEnv().equalsIgnoreCase("LOCAL")) {
				issue.setOs(System.getProperty("os.name"));
				issue.setOsBuild(System.getProperty("os.version"));
				issue.setPlatform(System.getProperty("os.arch"));
			} else {
				issue.setOs(GlobalUtil.getCommonSettings().getRemoteOS());
				issue.setPlatform(GlobalUtil.getCommonSettings().getRemoteOS());
				issue.setOsBuild(GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
			}
			issue.setSeverity(new MCAttribute(70, "crash"));
			issue.setReproducibility(new MCAttribute(10, "always"));

			issue.setSummary(summary + "\n" + new Date());
			issue.setDescription(description);
			issue.setCategory(category);
			issue.setPriority(new MCAttribute(40, "high"));
			issue.setAdditionalInformation(additionalInformation);

			// adding the issue to the project
			long id = sessions.addIssue(issue);

			// adding the screenshot to the issue
			if (!filename.equalsIgnoreCase("RestAPI")) {
				submitAttachment(sessions, id, new File(filename));
			}
			bugID = String.valueOf(id);
		} catch (MalformedURLException e) {
			LogUtil.errorLog(MantisReport.class, "Error no URL access to Mantis");
			e.printStackTrace();
		} catch (MCException e) {
			LogUtil.errorLog(MantisReport.class, "Error no connection to mantis");
			e.printStackTrace();
		}
		LogUtil.infoLog(MantisReport.class, "issue created successfully with Issue iD: " + bugID);
		return bugID;
	}

	/**
	 * Add attachment long.
	 *
	 * @param session  the session
	 * @param issueId  the issue id
	 * @param data     the data
	 * @param filename the filename
	 * @return the long
	 * @throws MCException the mc exception
	 */
	protected static long addAttachment(IMCSession session, long issueId, byte[] data, String filename)
			throws MCException {
		return session.addIssueAttachment(issueId, filename, getMimeType(filename), data);
	}

	/**
	 * Submit attachment long.
	 *
	 * @param session the session
	 * @param issueId the issue id
	 * @param file    the file
	 * @return the long
	 * @throws MCException the mc exception
	 */
	public static long submitAttachment(IMCSession session, long issueId, File file) throws MCException {
		try {

			byte[] data = readFile(file);
			String filename = null;
			if (doCompress(file)) {
				filename = getCompressedFilename(file);
				data = compress(data, file.getName());
			} else {
				filename = file.getName();
			}
			return addAttachment(session, issueId, data, filename);
		} catch (IOException e) {
			throw new MCException(e);
		}
	}

	/**
	 * Gets mime type.
	 *
	 * @param string the string
	 * @return the mime type
	 */
	public static String getMimeType(String string) {
		return new MimetypesFileTypeMap().getContentType(string);
	}

	/**
	 * Read file byte [ ].
	 *
	 * @param file the file
	 * @return the byte [ ]
	 * @throws IOException the io exception
	 */
	@SuppressWarnings("resource")
	public static byte[] readFile(File file) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int read = -1;
		while ((read = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, read);
		}
		return baos.toByteArray();
	}

	/**
	 * Do compress boolean.
	 *
	 * @param file the file
	 * @return the boolean
	 */
	public static boolean doCompress(File file) {
		return (!isCompressed(file));
	}

	/**
	 * Gets compressed filename.
	 *
	 * @param file the file
	 * @return the compressed filename
	 */
	protected static String getCompressedFilename(File file) {
		String filename;
		filename = file.getName() + ".zip";
		return filename;
	}

	/**
	 * Is compressed boolean.
	 *
	 * @param file the file
	 * @return the boolean
	 */
	protected static boolean isCompressed(File file) {
		String filename = file.getName();
		int lastSeparator = filename.lastIndexOf('.');
		if (lastSeparator > 0 && lastSeparator < filename.length()) {
			String type = filename.substring(lastSeparator + 1).toLowerCase();
			return Arrays.binarySearch(COMPRESSED_FILES, type) >= 0;
		} else {
			return false;
		}
	}

	/**
	 * The constant COMPRESSED_FILES.
	 */
	public final static String[] COMPRESSED_FILES = new String[]{"bz2", "gif", "gz", "jpg", "png", "rar", "zip"};

	/**
	 * Compress byte [ ].
	 *
	 * @param data     the data
	 * @param filename the filename
	 * @return the byte [ ]
	 * @throws IOException the io exception
	 */
	protected static byte[] compress(byte[] data, String filename) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		zos.putNextEntry(new ZipEntry(filename));
		zos.write(data);
		zos.close();
		data = baos.toByteArray();
		return data;
	}

}
