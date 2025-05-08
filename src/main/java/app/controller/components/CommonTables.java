package app.controller.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CommonTables {
    public static <T> void configureTable(
            TableView<T> table,
            List<TableColumn<T, ?>> columns,
            String[] propertyNames) {

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setCellValueFactory(new PropertyValueFactory<>(propertyNames[i]));
        }
    }

    public static void configureKandidatTable(TableView<Kandidatet> table) {
        TableColumn<Kandidatet, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Kandidatet, String> emriColumn = new TableColumn<>("Emri");
        TableColumn<Kandidatet, String> mbiemriColumn = new TableColumn<>("Mbiemri");
        TableColumn<Kandidatet, String> emailColumn = new TableColumn<>("Email");
        TableColumn<Kandidatet, String> phoneColumn = new TableColumn<>("Telefon");
        TableColumn<Kandidatet, String> dataRegjistrimitColumn = new TableColumn<>("Data e regjistrimit");
        TableColumn<Kandidatet, String> statusiColumn = new TableColumn<>("Statusi");

        List<TableColumn<Kandidatet, ?>> columns = List.of(
                idColumn, emriColumn, mbiemriColumn, emailColumn, phoneColumn, dataRegjistrimitColumn, statusiColumn
        );

        String[] propertyNames = {
                "idUser", "name", "surname", "email", "phoneNumber", "dataRegjistrimit", "statusiProcesit"
        };

        configureTable(table, columns, propertyNames);
    }

    public static void configureOrariTable(TableView<Orari> table) {
        TableColumn<Orari, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Orari, Integer> kandidatIdColumn = new TableColumn<>("ID Kandidat");
        TableColumn<Orari, Integer> stafIdColumn = new TableColumn<>("ID Staf");
        TableColumn<Orari, LocalDate> dataColumn = new TableColumn<>("Data");
        TableColumn<Orari, LocalTime> fillimiColumn = new TableColumn<>("Fillimi");
        TableColumn<Orari, LocalTime> mbarimiColumn = new TableColumn<>("Mbarimi");
        TableColumn<Orari, String> llojiColumn = new TableColumn<>("Lloji");
        TableColumn<Orari, String> statusiColumn = new TableColumn<>("Statusi");
        TableColumn<Orari, Integer> automjetiColumn = new TableColumn<>("Automjeti");

        List<TableColumn<Orari, ?>> columns = List.of(
                idColumn, kandidatIdColumn, stafIdColumn, dataColumn, fillimiColumn, mbarimiColumn, llojiColumn, statusiColumn, automjetiColumn
        );

        String[] propertyNames = {
                "idSesioni", "idKandidat", "idStaf", "dataSesionit", "oraFillimit", "oraPerfundimit", "llojiMesimit", "statusi", "idAutomjet"
        };

        configureTable(table, columns, propertyNames);
    }

    public static void configureStafiTable(TableView<Stafi> table) {
        TableColumn<Stafi, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Stafi, String> nameColumn = new TableColumn<>("Emri");
        TableColumn<Stafi, String> surnameColumn = new TableColumn<>("Mbiemri");
        TableColumn<Stafi, String> emailColumn = new TableColumn<>("Email");
        TableColumn<Stafi, String> phoneColumn = new TableColumn<>("Telefon");
        TableColumn<Stafi, LocalDate> dateOfBirthColumn = new TableColumn<>("Data e lindjes");
        TableColumn<Stafi, String> adresaColumn = new TableColumn<>("Adresa");
        TableColumn<Stafi, String> gjiniaColumn = new TableColumn<>("Gjinia");
        TableColumn<Stafi, String> roleColumn = new TableColumn<>("Roli");

        List<TableColumn<Stafi, ?>> columns = List.of(
                idColumn, nameColumn, surnameColumn, emailColumn, phoneColumn, dateOfBirthColumn, adresaColumn, gjiniaColumn, roleColumn
        );

        String[] propertyNames = {
                "idUser", "name", "surname", "email", "phoneNumber", "dateOfBirth", "adresa", "gjinia", "role"
        };

        configureTable(table, columns, propertyNames);
    }

    public static void configureFeedbackTable(TableView<FeedBack> table) {
        TableColumn<FeedBack, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<FeedBack, Integer> kandidatIdColumn = new TableColumn<>("ID Kandidat");
        TableColumn<FeedBack, Integer> stafIdColumn = new TableColumn<>("ID Staf");
        TableColumn<FeedBack, LocalDate> dataColumn = new TableColumn<>("Data");
        TableColumn<FeedBack, Integer> vlersimiColumn = new TableColumn<>("Vlersimi");
        TableColumn<FeedBack, String> komentColumn = new TableColumn<>("Koment");

        List<TableColumn<FeedBack, ?>> columns = List.of(
                idColumn, kandidatIdColumn, stafIdColumn, dataColumn, vlersimiColumn, komentColumn
        );

        String[] propertyNames = {
                "id", "idKandidat", "idStaf", "dataFeedback", "vlersimi", "koment"
        };

        configureTable(table, columns, propertyNames);
    }

    public static void configurePagesatTable(TableView<Pagesat> table) {
        TableColumn<Pagesat, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Pagesat, Integer> kandidatIdColumn = new TableColumn<>("ID Kandidat");
        TableColumn<Pagesat, Double> shumaColumn = new TableColumn<>("Shuma");
        TableColumn<Pagesat, LocalDate> dataPagesesColumn = new TableColumn<>("Data e pagesës");
        TableColumn<Pagesat, String> metodaPagesesColumn = new TableColumn<>("Metoda e pagesës");
        TableColumn<Pagesat, String> statusiPagesesColumn = new TableColumn<>("Statusi i pagesës");

        List<TableColumn<Pagesat, ?>> columns = List.of(
                idColumn, kandidatIdColumn, shumaColumn, dataPagesesColumn, metodaPagesesColumn, statusiPagesesColumn
        );

        String[] propertyNames = {
                "id", "idKandidat", "shuma", "dataPageses", "metodaPageses", "statusiPageses"
        };

        configureTable(table, columns, propertyNames);
    }

    public static void configureTestetTable(TableView<Testet> table) {
        TableColumn<Testet, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Testet, Integer> kandidatIdColumn = new TableColumn<>("ID Kandidat");
        TableColumn<Testet, Integer> stafIdColumn = new TableColumn<>("ID Staf");
        TableColumn<Testet, String> llojiTestitColumn = new TableColumn<>("Lloji i testit");
        TableColumn<Testet, LocalDate> dataTestitColumn = new TableColumn<>("Data e testit");
        TableColumn<Testet, String> rezultatiColumn = new TableColumn<>("Rezultati");
        TableColumn<Testet, Integer> piketColumn = new TableColumn<>("Piket");

        List<TableColumn<Testet, ?>> columns = List.of(
                idColumn, kandidatIdColumn, stafIdColumn, llojiTestitColumn, dataTestitColumn, rezultatiColumn, piketColumn
        );

        String[] propertyNames = {
                "id", "idKandidat", "idStaf", "llojiTestit", "dataTestit", "rezultati", "piket"
        };

        configureTable(table, columns, propertyNames);
    }

    public static void configureDokumentetTable(TableView<Dokumentet> table) {
        TableColumn<Dokumentet, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Dokumentet, Integer> kandidatIdColumn = new TableColumn<>("ID Kandidat");
        TableColumn<Dokumentet, String> llojiDokumentitColumn = new TableColumn<>("Lloji i dokumentit");
        TableColumn<Dokumentet, String> emriSkedaritColumn = new TableColumn<>("Emri i skedarit");
        TableColumn<Dokumentet, LocalDate> dataNgarkimitColumn = new TableColumn<>("Data e ngarkimit");

        List<TableColumn<Dokumentet, ?>> columns = List.of(
                idColumn, kandidatIdColumn, llojiDokumentitColumn, emriSkedaritColumn, dataNgarkimitColumn
        );

        String[] propertyNames = {
                "id", "idKandidat", "llojiDokumentit", "emriSkedarit", "dataNgarkimit"
        };

        configureTable(table, columns, propertyNames);
    }
}
