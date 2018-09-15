package net.yoplitein.wurm.pricemod;

import java.lang.reflect.Field;

import com.wurmonline.server.items.ItemTemplate;
import com.wurmonline.server.items.ItemTemplateFactory;
import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.interfaces.ItemTemplatesCreatedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

public class PriceMod implements WurmServerMod, ItemTemplatesCreatedListener
{
    Field valueField;
    
    @Override
    public void onItemTemplatesCreated()
    {
        try
        {
            valueField = ItemTemplate.class.getDeclaredField("value");
            
            valueField.setAccessible(true);
        }
        catch(Exception err)
        {
            throw new HookException(err);
        }
        
        setItemPrice(601, 2500); //shaker orb
    }
    
    private void setItemPrice(int id, int newPrice)
    {
        ItemTemplate template = ItemTemplateFactory.getInstance().getTemplateOrNull(id);
        
        if(template == null)
            throw new HookException(String.format("Couldn't find template with id %d", id));
        
        try
        {
            valueField.setInt(template, newPrice);
        }
        catch(Exception err)
        {
            throw new HookException(err);
        }
    }
}
