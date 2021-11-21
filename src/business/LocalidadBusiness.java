package business;

import java.util.List;

import businessInterface.ILocalidadBusiness;
import dao.LocalidadDAO;
import entity.Localidad;

public class LocalidadBusiness implements ILocalidadBusiness {

    LocalidadDAO localidadDAO = new LocalidadDAO();

    @Override
    public List<Localidad> selectAll() {
        return localidadDAO.selectAll();
    }

    @Override
    public Localidad selectOne(int id) {
        return localidadDAO.selectOne(id);
    }
}
