package backend.dao.impl;

import backend.dao.IDao;
import backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoEnMemoria implements IDao<Odontologo> {
    private Logger LOGGER = Logger.getLogger(OdontologoEnMemoria.class);
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Integer id = odontologos.size() + 1;
        odontologo.setId(id);

        odontologos.add(odontologo);
        LOGGER.info("Odontólogo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        return odontologos;
    }
}