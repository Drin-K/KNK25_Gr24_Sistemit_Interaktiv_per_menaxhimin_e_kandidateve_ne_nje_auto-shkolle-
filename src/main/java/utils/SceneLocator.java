package utils;

public class SceneLocator {
    public static final String LOGIN_PAGE="/app/login_in.fxml";
    public static final String SIGNUP_PAGE="/app/sign_up.fxml";
    public static final String FRONT_PAGE= "/app/candidateFXML/FrontPage.Konsumator.fxml";
    public static final String CATEGORY_PAGE= "/app/candidateFXML/candidate_category.fxml";
    public static final String HOME_PAGE= "/app/candidateFXML/candidate_home.fxml";
    public static final String SCHEDULER_PAGE= "/app/candidateFXML/candidate_scheduler.fxml";
    public static final String TESTS_PAGE= "/app/candidateFXML/candidate_test.fxml";
    public static final String PAYMENTS_PAGE= "/app/candidateFXML/candidate_payments.fxml";
    public static final String PROGRESS_PAGE= "/app/candidateFXML/candidate_progress.fxml";
    public static final String FEEDBACK_PAGE= "/app/candidateFXML/candidate_feedback.fxml";
    public static final String PROFILE_PAGE= "/app/candidateFXML/candidate_profile.fxml";
    private static String currentRightPage = HOME_PAGE;

    //PER ADMIN!
    public static final String FRONT_PAGE_ADMIN="/app/adminFXML/FrontPage.Admin.fxml";
    public static final String LICENSES_PAGE="/app/adminFXML/admin_licenses.fxml";
    public static final String STAF_PAGE="/app/adminFXML/admin_manage_staf.fxml";
    public static final String CANDIDATES_PAGE="/app/adminFXML/admin_manage_candidates.fxml";
    public static final String PAYMENTS_MANAGE_PAGE="/app/adminFXML/admin_payments.fxml";

    public static void setCurrentRightPage(String path) {
        currentRightPage = path;
    }

    public static String getCurrentRightPage() {
        return currentRightPage;
    }
}
