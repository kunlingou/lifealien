import xml.etree.ElementTree as ET


class XmlConverter:

    attrType = ['str', 'float', 'bool']

    @staticmethod
    def class_to_xml(classObj):
        className = classObj.__class__.__name__
        root = ET.Element(className)
        attrs = []
        try:
            attrs = classObj.__dict__.keys()  # 获取该对象的所有属性(即成员变量)
        except Exception:
            attrs = attrs or []
        if len(attrs) > 0:
            for attr in attrs:
                attrvalue = getattr(classObj, attr)
                valueType = attrvalue.__class__.__name__
                if valueType in XmlConverter.attrType:
                    root.set(attr, str(attrvalue))
                else:
                    if attrvalue.__class__.__name__ != 'list':
                        attrvalue = [attrvalue]
                    for obj in attrvalue:
                        root.append(XmlConverter.class_to_xml(obj))
        return root

