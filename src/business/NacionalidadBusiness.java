package business;

import java.util.List;

import businessInterface.INacionalidadBusiness;
import dao.NacionalidadDAO;
import entity.Nacionalidad;

public class NacionalidadBusiness implements INacionalidadBusiness {

    NacionalidadDAO nacionalidadDAO = new NacionalidadDAO();

    @Override
    public List<Nacionalidad> selectAll() {
        return nacionalidadDAO.selectAll();
    }

    @Override
    public Nacionalidad selectOne(int id) {
        return nacionalidadDAO.selectOne(id);
    }
}
