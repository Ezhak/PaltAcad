package daoInterface;

import java.util.List;

import entity.Nacionalidad;

public interface INacionalidadDAO {

    List<Nacionalidad> selectAll();

    Nacionalidad selectOne(int id);
}
