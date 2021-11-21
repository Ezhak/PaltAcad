package business;

import java.util.List;

import businessInterface.IAlumnoBusiness;
import dao.AlumnoDAO;
import entity.Alumno;

public class AlumnoBusiness implements IAlumnoBusiness {

    AlumnoDAO alumnoDAO = new AlumnoDAO();

    @Override
    public boolean delete(Alumno alumno) {
        return alumnoDAO.delete(alumno);
    }

    @Override
    public boolean insert(Alumno alumno) {
        return alumnoDAO.insert(alumno);
    }

    @Override
    public List<Alumno> selectAll() {
        return alumnoDAO.selectAll();
    }

    @Override
    public List<Alumno> selectAllFromCurso(int id) {
        return alumnoDAO.selectAllFromCurso(id);
    }

    @Override
    public Alumno selectOne(int legajo) {
        return alumnoDAO.selectOne(legajo);
    }

    @Override
    public boolean update(Alumno alumno) {
        return alumnoDAO.update(alumno);
    }
}
