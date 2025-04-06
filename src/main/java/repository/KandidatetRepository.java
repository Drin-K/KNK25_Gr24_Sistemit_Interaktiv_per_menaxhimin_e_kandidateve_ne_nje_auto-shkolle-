package repository;

import Database.DBConnector;
import models.Dto.kandidatet.CreateKandidatetDto;
import models.Dto.kandidatet.UpdateKandidatetDto;
import models.Kandidatet;

import java.sql.*;
import java.util.ArrayList;

public class KandidatetRepository extends BaseRepository<Kandidatet, CreateKandidatetDto, UpdateKandidatetDto> {
    public KandidatetRepository(){
        super("kandidatet");
    }

    public Kandidatet fromResultSet(ResultSet result) throws SQLException{
        return Kandidatet.getInstance(result);
    }


    public Kandidatet create(CreateKandidatetDto kandidatetDto){
        String query= """
                INSERT INTO KANDIDATET(EMRI, MBIEMRI, DATELINDJA, GJINIA, NUMRI_TELEFONIT, EMAIL, ADRESA, DATA_REGJISTRIMIT, STATUSI_I_PROCESIT) 
                VALUES(?,?,?,?,?,?,?,?,?)
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1,kandidatetDto.getEmri());
            pstm.setString(2, kandidatetDto.getMbiemri());
            pstm.setObject(3, kandidatetDto.getDatelindja());
            pstm.setString(4, kandidatetDto.getGjinia());
            pstm.setString(5, kandidatetDto.getNumriTelefonit());
            pstm.setString(6, kandidatetDto.getEmail());
            pstm.setString(7, kandidatetDto.getAdresa());
            pstm.setObject(8, kandidatetDto.getDataRegjistrimit());
            pstm.setString(9, kandidatetDto.getStatusiProcesit());

            pstm.execute();
            ResultSet res=pstm.getGeneratedKeys();
            if(res.next()){
                int id=res.getInt(1);

                 return this.getById(id);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Kandidatet update(UpdateKandidatetDto kandidatetDto){
        String query= """
                UPDATE KANDIDATET 
                SET EMAIL=?
                WHERE ID=?
                """;
        try{
            PreparedStatement pstm=this.connection.prepareStatement(query);

            pstm.setString(1,kandidatetDto.getEmail());
            pstm.setInt(2, kandidatetDto.getId());
            int updatedRecords=pstm.executeUpdate();
            if(updatedRecords==1){
                return this.getById(kandidatetDto.getId());
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //    public ArrayList<Kandidatet> getAll(){
//        java.util.ArrayList<Kandidatet>kandidatet=new ArrayList<>();
//        String query="SELECT * FROM STAFI";
//        try{
//            Statement stm=this.connection.createStatement();
//            ResultSet res=stm.executeQuery(query);
//            while(res.next()){
//                kandidatet.add(Kandidatet.getInstance(res));
//
//            }
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        return kandidatet;
//    }

//    public Kandidatet getById(int id){
//        String query="SELECT * FROM KANDIDATET WHERE ID_KANDIDAT = ?";
//        try{
//            PreparedStatement pstm=this.connection.prepareStatement(query);
//            pstm.setInt(1,id);
//            ResultSet res=pstm.executeQuery();
//            if(res.next()){
//                return Kandidatet.getInstance(res);
//            }
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
}


