package backend.service;

import backend.dao.IDao;
import backend.model.Odontologo;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    private OdontologoService(IDao<Odontologo> odontologoIDao){this.odontologoIDao = odontologoIDao;}

    public Odontologo guardarOdontologo(Odontologo odontologo){return odontologoIDao.guardar(odontologo);}

    public List<Odontologo> buscarTodos(){return odontologoIDao.listar();}
}
