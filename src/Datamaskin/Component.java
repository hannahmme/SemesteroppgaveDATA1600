package Datamaskin;

import javafx.scene.control.CheckBox;

public class Component {
/*
    private int index = 1;*/
    private String componentInfo;
    private String componentName;
    private int componentPrice;
    private CheckBox checkbox;

    public Component(String componentName, String componentInfo, int componentPrice, CheckBox checkbox) {
        this.componentInfo = componentInfo;
        this.componentName = componentName;
        this.componentPrice = componentPrice;
        this.checkbox = checkbox;
    }


    @Override
    public String toString(){
        String utskrift = "";
        utskrift += "Produktnavn: " + componentName + "\nPris: "+componentPrice;
        return utskrift;
    }

    public String getComponentInfo() {
        return componentInfo;
    }

    public void setComponentInfo(String componentInfo) {
        this.componentInfo = componentInfo;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getComponentPrice() {
        return componentPrice;
    }

    public void setComponentPrice(int componentPrice) {
        this.componentPrice = componentPrice;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }




}
