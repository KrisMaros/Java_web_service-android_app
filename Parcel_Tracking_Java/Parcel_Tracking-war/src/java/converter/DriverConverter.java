package converter;

import DTO.DriverDTO;
import DTO.StatusDTO;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "driverConverter")
public class DriverConverter implements Converter, Serializable
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        String[] part = value.split(":");
        return new DriverDTO(Integer.valueOf(part[0]),"","","","",0);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (value != null
                && value instanceof DriverDTO)
        {
            DriverDTO module = (DriverDTO) value;
            return module.toString();
        }

        return "";
    }

}
