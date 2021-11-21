package business;

import java.util.List;

import businessInterface.IProvinciaBusiness;
import dao.ProvinciaDAO;
import entity.Provincia;

public class ProvinciaBusiness implements IProvinciaBusiness {

    ProvinciaDAO provinciaDAO = new ProvinciaDAO();

    @Override
    public List<Provincia> selectAll() {
        return provinciaDAO.selectAll();
    }

    @Override
    public Provincia selectOne(int id) {
        return provinciaDAO.selectOne(id);
    }
}
