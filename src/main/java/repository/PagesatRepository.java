package repository;
import models.Dto.pagesat.CreatePagesatDto;

import models.Pagesat;
import models.Dto.pagesat.UpdatePagesatDto;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
                Pagesat(ID_Kandidat, Shuma, Data_e_Pageses, Medota_e_Pageses, Statusi_i_Pageses)
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
        StringBuilder query = new StringBuilder("UPDATE Pagesat SET ");
        ArrayList<Object> params = new ArrayList<>();
        if (pagesatDto.getShuma() !=0){
            query.append("Shuma=?, ");
            params.add(pagesatDto.getShuma());
        }
        if (pagesatDto.getMetodaPageses()!=null){
            query.append("Metoda_e_Pageses=?, ");
            params.add(pagesatDto.getMetodaPageses());
        }
        if (pagesatDto.getStatusiPageses()!=null){
            query.append("Statusi_i_Pageses=?, ");
        }
        if (params.isEmpty()) {
            return getById(pagesatDto.getId());
        }
        query.setLength(query.length() - 2);//me largu "? "->se paraqet gabim ne sintakse
        query.append(" WHERE id = ?");
        params.add(pagesatDto.getId());
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(pagesatDto.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gabim gjatë përditësimit të pagesave", e);
        }
        return null;
    }
    public ArrayList<Pagesat> findByStatus(String statusi) {
        //Funksion i cili gjen te gjitha pagesat ne baze te statusit
        ArrayList<Pagesat> pagesatList = new ArrayList<>();
        String query = "SELECT * FROM Pagesat WHERE Statusi_i_Pageses = ?";

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, statusi);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Pagesat pagesa = fromResultSet(rs);
                pagesatList.add(pagesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pagesatList;
    }

}