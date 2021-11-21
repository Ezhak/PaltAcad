package business;

import java.util.List;

import businessInterface.IMateriaBusiness;
import dao.MateriaDAO;
import entity.Materia;

public class MateriaBusiness implements IMateriaBusiness {

    MateriaDAO materiaDAO = new MateriaDAO();

    @Override
    public List<Materia> selectAll() {
        return materiaDAO.selectAll();
    }

    @Override
    public Materia selectOne(int id) {
        return materiaDAO.selectOne(id);
    }
}
