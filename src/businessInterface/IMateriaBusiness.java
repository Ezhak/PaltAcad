package businessInterface;

import java.util.List;

import entity.Materia;

public interface IMateriaBusiness {

    List<Materia> selectAll();

    Materia selectOne(int id);
}
