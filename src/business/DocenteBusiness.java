package business;

import java.util.List;

import businessInterface.IDocenteBusiness;
import dao.DocenteDAO;
import entity.Docente;

public class DocenteBusiness implements IDocenteBusiness {

    DocenteDAO docenteDAO = new DocenteDAO();

    @Override
    public boolean delete(Docente docente) {
        return docenteDAO.delete(docente);
    }

    @Override
    public boolean insert(Docente docente) {
        return docenteDAO.insert(docente);
    }

    @Override
    public List<Docente> selectAll() {
        return docenteDAO.selectAll();
    }

    @Override
    public Docente selectOne(int legajo) {
        return docenteDAO.selectOne(legajo);
    }

    @Override
    public Docente selectOne(int legajo, String contrasenia) {
        return docenteDAO.selectOne(legajo, contrasenia);
    }

    @Override
    public boolean update(Docente docente) {
        return docenteDAO.update(docente);
    }
}
