package net.yoplitein.wurm.pricemod;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Properties;

import com.wurmonline.server.items.ItemTemplate;
import com.wurmonline.server.items.ItemTemplateFactory;
import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.ItemTemplatesCreatedListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

public class PriceMod implements WurmServerMod, ItemTemplatesCreatedListener, Configurable
{
    Field valueField;
    HashMap<Integer, Integer> priceMods = new HashMap<>();
    
    @Override
    public void configure(Properties properties)
    {
        for(String key: properties.stringPropertyNames())
        {
            if(!key.startsWith("price"))
                continue;
            
            String value = properties.getProperty(key);
            String[] bits = value.split(",");
            int id = Integer.parseInt(bits[0]);
            int price = Integer.parseInt(bits[1]);
            
            priceMods.put(id, price);
        }
    }
    
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
        
        for(int id: priceMods.keySet())
        {
            try
            {
                setItemPrice(id, priceMods.get(id));
            }
            catch(HookException err)
            {
                throw new RuntimeException(String.format("Failed to set price of item %d", id), err);
            }
        }
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
