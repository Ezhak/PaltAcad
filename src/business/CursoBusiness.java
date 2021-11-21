package business;

import java.util.List;

import businessInterface.ICursoBusiness;
import dao.CursoDAO;
import entity.Curso;

public class CursoBusiness implements ICursoBusiness {

    CursoDAO cursoDAO = new CursoDAO();

    @Override
    public boolean insert(Curso curso) {
        return cursoDAO.insert(curso);
    }

    @Override
    public List<Curso> selectAll() {
        return cursoDAO.selectAll();
    }

    @Override
    public List<Curso> selectAll(int legajo) {
        return cursoDAO.selectAll(legajo);
    }

    @Override
    public Curso selectOne(int id) {
        return cursoDAO.selectOne(id);
    }
}
