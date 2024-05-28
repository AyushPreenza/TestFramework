package mantisutil;

import utilities.GlobalUtil;

/**
 * The interface Constantes.
 */
public interface IConstantes {
    /**
     * The constant MANTIS_URL.
     */
    String MANTIS_URL = "http://" + GlobalUtil.getCommonSettings().getbugToolHostName()
            + "/bugTool/api/soap/bugToolconnect.php";
    /**
     * The constant MANTIS_USER.
     */
    String MANTIS_USER = GlobalUtil.getCommonSettings().getbugToolUserName();
    /**
     * The constant MANTIS_PWD.
     */
    String MANTIS_PWD = GlobalUtil.getCommonSettings().getbugToolPassword();
    /**
     * The constant MANTIS_PROJET.
     */
    String MANTIS_PROJET = GlobalUtil.getCommonSettings().getbugToolProjectName();
}
