package daoInterface;

import java.util.List;

import entity.Notas;

public interface INotasDAO {

    boolean insert(Notas notas);

    boolean update(Notas notas);

    List<Notas> selectAll(int id);
}
