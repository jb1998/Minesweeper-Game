package me.jaxbot.minesweeper;

import android.content.Context;
import android.widget.Button;

/**
 * Created by pc on 1/28/2017.
 */

public class Mybutton extends Button{
    private String status="0";
    private int addressx;
    private int addressy;
    public Mybutton(Context context)
    {
        super(context);
    }
    public int Setaddressx(int addressx)
    {
        this.addressx=addressx;
        return addressx;
    }
    public String getStatus() {
        return status;
    }

    public int Setaddressy(int addressy)
    {
        this.addressy=addressy;
        return addressy;
    }
public int getAddressx()
{
    return addressx;
}
    public int getAddressy()
    {
        return addressy;
    }

    public void setStatus(String status) {
        this.status = status;

    }
    public String getstatus()
    {
         return status;
    }
}
