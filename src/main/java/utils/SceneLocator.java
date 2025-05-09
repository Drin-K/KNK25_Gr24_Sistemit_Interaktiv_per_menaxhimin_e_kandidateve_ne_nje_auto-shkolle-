package utils;

import javafx.scene.layout.Pane;

public class SceneLocator {
    //Kandidati
    public static final String LOGIN_PAGE="/app/log_in.fxml";
    public static final String SIGNUP_PAGE="/app/sign_up.fxml";
    public static final String FRONT_PAGE= "/app/candidateFXML/FrontPage.Konsumator.fxml";
    public static final String CATEGORY_PAGE= "/app/candidateFXML/candidate_category.fxml";
    public static final String HOME_PAGE= "/app/candidateFXML/candidate_home.fxml";
    public static final String SCHEDULER_PAGE= "/app/candidateFXML/candidate_scheduler.fxml";
    public static final String TESTS_PAGE= "/app/candidateFXML/candidate_test.fxml";
    public static final String PAYMENTS_PAGE= "/app/candidateFXML/candidate_payments.fxml";
    public static final String PAYMENTS_CASH= "/app/candidateFXML/candidate_payments_cash.fxml";

    public static final String PROGRESS_PAGE= "/app/candidateFXML/candidate_progress.fxml";
    public static final String FEEDBACK_PAGE= "/app/candidateFXML/candidate_feedback.fxml";
    public static final String PROFILE_PAGE= "/app/candidateFXML/candidate_profile.fxml";
    public static final String DOCUMENTS_PAGE_A = "/app/candidateFXML/candidate_document.fxml";
    private static String currentRightPage = HOME_PAGE;


    // Stafi Paths
    public static final String INSTRUCTOR_FRONT_PAGE = "/app/stafiFXML/FrontPage_instructor.fxml";
    public static final String INSTRUCTOR_FEEDBACK = "/app/stafiFXML/feedbackInstructor.fxml";
    public static final String INSTRUCTOR_SCHEDULER = "/app/stafiFXML/instructorScheduler.fxml";
    public static final String MANAGE_TESTS = "/app/stafiFXML/testManagerInstructor.fxml";
    public static final String MANAGE_VEHICLES = "/app/stafiFXML/manageVehicles.fxml";
    public static final String REPORTS = "/app/stafiFXML/raports.fxml";
    public static final String INSTRUCTOR_PROFILE = "/app/stafiFXML/instructorProfile.fxml";
    public static final String INSTRUCTOR_HOME = "/app/stafiFXML/InstructorHome.fxml";
    public static final String INSTRUCTOR_EDIT="/app/stafiFXML/edit_scheduler.fxml";
    public static final String INSTRUCTOR_PUT_SERVICE = "/app/stafiFXML/putVehicle_to_service.fxml";

    //PER ADMIN!
    public static final String FRONT_PAGE_ADMIN="/app/adminFXML/FrontPage.Admin.fxml";
    public static final String LICENSES_PAGE="/app/adminFXML/admin_licenses.fxml";
    public static final String STAF_PAGE="/app/adminFXML/admin_manage_staf.fxml";
    public static final String CANDIDATES_PAGE="/app/adminFXML/admin_manage_candidates.fxml";
    public static final String PAYMENTS_MANAGE_PAGE="/app/adminFXML/admin_payments.fxml";
    public static final String ADMIN_HOME_PAGE="/app/adminFXML/admin_home.fxml";
    public static final String UPDATE_ADMIN_CANDIDATE_PAGE="/app/adminFXML/update_candidate_or_staf.fxml";
    public static final String PROGRESS_ADMIN_REPORT_PAGE="/app/adminFXML/progress_report.fxml";

    // Over all
    public static final String CHANGE_PASSWORD= "/app/changePass.fxml";

    public static void setCurrentRightPage(String path) {
        currentRightPage = path;
    }

    public static String getCurrentRightPage() {
        return currentRightPage;
    }

}
