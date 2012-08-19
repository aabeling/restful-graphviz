package de.banapple.graphviz.controller.commands;

public class Dot2Svg
{
    private String dot;
    
    public String getDot()
    {
        return dot;
    }
    
    public void setDot(String dot)
    {
        this.dot = dot;
    }
    
    @Override
    public String toString()
    {
        return dot;
    }
}
