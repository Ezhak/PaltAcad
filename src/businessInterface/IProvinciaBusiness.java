package businessInterface;

import java.util.List;

import entity.Provincia;

public interface IProvinciaBusiness {

    List<Provincia> selectAll();

    Provincia selectOne(int id);
}
