package repository;
import models.Dto.pagesat.CreatePagesatDto;

import models.Pagesat;
import models.Dto.pagesat.UpdatePagesatDto;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PagesatRepository extends BaseRepository<Pagesat, CreatePagesatDto, UpdatePagesatDto>{

    public PagesatRepository(){
        super("pagesat");
    }

    public Pagesat fromResultSet(ResultSet result) throws SQLException {
        return Pagesat.getInstance(result);
    }

    public Pagesat create(CreatePagesatDto pagesatDto){
        String query = """
                INSERT INTO 
                PAGESAT (id_kandidat, shuma , data_e_pageses, medota_e_pageses, statusi_i_pageses)
                VALUES (?, ?, ?, ?, ?);
                """;
        try{

            PreparedStatement pstm =
                    this.connection.prepareStatement(
                            query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, pagesatDto.getIdKandidat());
            pstm.setDouble(2, pagesatDto.getShuma());
            pstm.setObject(3, pagesatDto.getDataPageses());
            pstm.setString(4, pagesatDto.getMetodaPageses());
            pstm.setString(5, pagesatDto.getStatusiPageses());
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            if(res.next()){
                int id = res.getInt(1);
                return this.getById(id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Pagesat update(UpdatePagesatDto pagesatDto) {
//        String query = """
//                UPDATE USERS
//                SET EMAIL = ?
//                WHERE ID = ?
//                """;
//        try{
//            PreparedStatement pstm = this.connection.prepareStatement(query);
//            pstm.setString(1, pagesatDto.getEmail());
//            pstm.setInt(2, pagesatDto.getId());
//            int updatedRecords = pstm.executeUpdate();
//            if(updatedRecords == 1){
//                return this.getById(pagesatDto.getId());
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
        return null;
    }
    }