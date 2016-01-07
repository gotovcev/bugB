package ru.rt.year2015;

import org.dom4j.Element;

public class MessageData {
    String DId;
    public MessageData(String DicId){
        DId = DicId;
    }

    public void CreateMesageData (Element root) {
        Element MessageData = root.addElement("smev:MessageData")
                .addAttribute("xmlns:smev", "http://smev.gosuslugi.ru/rev120315");
        Element AppData = MessageData.addElement("smev:AppData");
        Element DictionaryId = AppData.addElement("atc:DictionaryId");
        DictionaryId.addText(DId);
    }
}
