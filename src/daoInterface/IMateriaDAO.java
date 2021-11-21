package daoInterface;

import java.util.List;

import entity.Materia;

public interface IMateriaDAO {

    List<Materia> selectAll();

    Materia selectOne(int id);
}
