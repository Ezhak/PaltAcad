package daoInterface;

import java.util.List;

import entity.Provincia;

public interface IProvinciaDAO {

    List<Provincia> selectAll();

    Provincia selectOne(int idProvincia);
}
