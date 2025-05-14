<div align="center">
<img src="src/main/resources/images/CarLogo.png" alt="Car Logo" width="200"/>
</div>

<h1 align="center"> Sistemi për Menaxhimin e Kandidatëve në një Auto-shkollë </h1>

---
## Përmbledhje e projektit
Ky projekt përfaqëson një aplikacion desktop të ndërtuar me **JavaFX** për menaxhimin e plotë të kandidatëve në një auto-shkollë. Përfshin role të ndryshme si **Admin**, **Instruktor** dhe **Kandidat**, gjithashtu adreson nevojat administrative dhe funksionale të një auto-shkolle përmes një sistemi efektiv.

---

## Struktura e projektit

Struktura e aplikacionit ndjek parimin **MVC (Model-View-Controller)** dhe është e organizuar:

- **src/main/java/app:** Përmban files kryesorë të applikacionit:
  - Main
  - Folderin controller
- **src/main/java/app/controller:** Përmban kontrolluesit për funksionalitete:
  - `BaseController`
  - `AdminHomeController`
  - `AdminPatentaController`
  - `AdminPaymentController`
  - `CandidateManagmentController`
  - `CandidateProfileController`
  - `CategoryConsumatorController`
  - `ChangePasswordController`
  - `CreateRaportiController`
  - `DokumentetKandidatiController`
  - `EditScheduleController`
  - `FeedbackConsumatorController`
  - `FrontPageAdminController`
  - `FrontPageConsumatorController`
  - `FrontPageInstructorController`
  - `InstructorManageVehicleController`
  - `InstructorSchedulerController`
  - `LogInController`
  - `OrariKandidatiController`
  - `PagesatHandleController`
  - `RaportiProgresitCandidateController`
  - `SignUpController`
  - `TestManagerController`
  - `TestResultCandidateController`
  - `UpdateUserController`
- **src/main/resources/app:** Përmban FXML files që përcaktojnë ndërfaqen/GUI-në e përdoruesit:
- `changePass.fxml`
- `log_in.fxml`
- `sign_up.fxml`

  - **adminFXML:**
    - `admin_home.fxml`
    - `admin_licenses.fxml`
    - `admin_manage_candidates.fxml`
    - `admin_manage_staf.fxml`
    - `admin_payments.fxml`
    - `FrontPage.Admin.fxml`
    - `update_candidate_or_staf.fxml`
  - **candidateFXML:**
    - `candidate_category.fxml`
    - `candidate_document.fxml`
    - `candidate_feedback.fxml`
    - `candidate_home.fxml`
    - `candidate_payments.fxml`
    - `candidate_payments_cash.fxml`
    - `candidate_profile.fxml`
    - `candidate_progress.fxml`
    - `candidate_scheduler.fxml`
    - `candidate_test.fxml`
    - `FrontPage.Konsumator.fxml`
  - **stafiFXML:**
    - `edit_scheduler.fxml`
    - `feedbackInstructor.fxml`
    - `FrontPage_instructor.fxml`
    - `instructorHome.fxml`
    - `instructorProfile.fxml`
    - `instructorScheduler.fxml`
    - `manageVehicles.fxml`
    - `putVehicle_to_service.fxml`
    - `raports.fxml`
    - `testManagerInstructor.fxml`
---

## Karakteristikat dhe funksionalitetet
### Faqja e adminit
Faqja e Adminit është qendra e kontrollit për menaxhimin e gjithë aplikacionit. Admini ka akses në funksionalitete të ndryshme përfshirë:

- **Menaxhimi i Licensave**
- **Menaxhimi i Kandidatëve**
- **Menaxhimi i Stafit**
- **Menaxhimi i Pagesave** 

### Faqja e instruktorit
Faqja e instruktorit menaxhon te gjithë kandidatët të regjistruar në aplikacion dhe ka aksese në funsksionalitete të ndryshme si:
- **Menaxhimi i Orareve**
- **Menaxhimi i testeve teorike/praktike**
- **Menaxhimi i automjeteve**
- **Menaxhimi i raporteve**
- **Menaxhimi i feedbacks**

## Faqja e kandidatit
Faqja e kandidatit është vendi në të cilin kandidatët mund të aplikojnë dhe të regjistrohen për të marr patentën e shoferit. Gjithashtu mund të mbikqyrin orarin, pikët e marra nga testet teorike/praktike, bërja e pagesave dhe shumë funksionalitete:
- **Regjistrimi i kategorisë**
- **Mbikqyrja e orareve**
- **Mbikqyrja e pikëve të testeve teorike/praktike**
- **Menaxhimi i pagesave**
- **Mbikqyrja e raportit të progresit**
- **Feedback për instruktorët**

---

## Si të instalohet dhe përdoret
### Tools të nevojshëm për përdorim të applikacionit:

- **Java Developement Kit (JDK) 8 ose më lart.**
- **JavaFX SDK**
- **Një IDE si IntelliJ**
- **SceneBuilder**

1. **Klono repozitorin:**

   ```bash
   git clone https://github.com/Drin-K/KNK25_Gr24_Sistemit_Interaktiv_per_menaxhimin_e_kandidateve_ne_nje_auto-shkolle-.git
---
## Punuan
- **Drin Kurti**
- **Albena Veseli**
- **Bardh Tahiri**
- **Mehmet Mehmeti**

<a href="#top">⬆️ <b>Kthehu në fillim </b> </a>

