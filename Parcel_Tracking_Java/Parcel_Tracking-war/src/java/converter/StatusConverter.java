
package converter;

import DTO.StatusDTO;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "statusConverter")
public class StatusConverter implements Converter, Serializable
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        String[] part = value.split(":");
        return new StatusDTO(Integer.valueOf(part[0]),"");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        if (value != null
                && value instanceof StatusDTO)
        {
            StatusDTO module = (StatusDTO) value;
            return module.toString();
        }

        return "";
    }

}
