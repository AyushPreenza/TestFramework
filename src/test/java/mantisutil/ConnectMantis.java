package mantisutil;

import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.MCException;
import org.mantisbt.connect.axis.MCSession;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The type Connect mantis.
 */
public class ConnectMantis {

	private static ConnectMantis instance = null;
	private static IMCSession session = null;
	/**
	 * The constant MANTIS_URL.
	 */
	public static String MANTIS_URL = null;
	/**
	 * The constant MANTIS_USER.
	 */
	public static String MANTIS_USER = null;
	/**
	 * The constant MANTIS_PWD.
	 */
	public static String MANTIS_PWD = null;
	/**
	 * The constant MANTIS_PROJET.
	 */
	public static String MANTIS_PROJET = null;

	/**
	 * Instantiates a new Connect mantis.
	 *
	 * @throws MalformedURLException the malformed url exception
	 * @throws MCException           the mc exception
	 */
	public ConnectMantis() throws MalformedURLException, MCException {
		URL url = new URL(MANTIS_URL);
		session = new MCSession(url, MANTIS_USER, MANTIS_PWD);
	}


	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static ConnectMantis getInstance() {
		if (instance == null) {
			try {
				instance = new ConnectMantis();
			} catch (MalformedURLException | MCException ex) {
				Logger.getLogger(ConnectMantis.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return instance;
	}


	/**
	 * Gets session.
	 *
	 * @return the session
	 * @throws MalformedURLException the malformed url exception
	 * @throws MCException           the mc exception
	 */
	public static IMCSession getSession() throws MalformedURLException, MCException {
		if (session == null) {
			getInstance();
		}
		return session;
	}
}
