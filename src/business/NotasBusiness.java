package business;

import java.util.List;

import businessInterface.INotasBusiness;
import dao.NotasDAO;
import entity.Notas;

public class NotasBusiness implements INotasBusiness {

    NotasDAO notasDAO = new NotasDAO();

    @Override
    public boolean insert(Notas notas) {
        return notasDAO.insert(notas);
    }

    @Override
    public boolean update(Notas notas) {
        return notasDAO.update(notas);
    }

    @Override
    public List<Notas> selectAll(int id) {
        return notasDAO.selectAll(id);
    }

}
