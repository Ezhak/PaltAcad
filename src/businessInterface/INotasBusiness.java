package businessInterface;

import java.util.List;

import entity.Notas;

public interface INotasBusiness {

    boolean insert(Notas notas);

    boolean update(Notas notas);

    List<Notas> selectAll(int id);
}
