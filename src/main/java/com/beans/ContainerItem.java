package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ContainerBean", propOrder = {
    "containers"
})
@XmlRootElement(name = "root")
public class ContainerItem extends Root {

    private List<ContainerItemBean> containerItemList;

    public ContainerItem() {
        super(0, "Success");
    }

    public ContainerItem(List<ContainerItemBean> containerItemList) {
        super(0, "Success");
        this.containerItemList = containerItemList;
    }

    public List<ContainerItemBean> getContainerItemList() {
        return containerItemList;
    }

    public void setContainerItemList(List<ContainerItemBean> containerItemList) {
        this.containerItemList = containerItemList;
    }
}
