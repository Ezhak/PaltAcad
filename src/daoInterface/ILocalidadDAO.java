package daoInterface;

import java.util.List;

import entity.Localidad;

public interface ILocalidadDAO {

    List<Localidad> selectAll();

    Localidad selectOne(int id);
}
