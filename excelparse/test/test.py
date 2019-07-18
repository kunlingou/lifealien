from xml.etree import ElementTree as ET


# 创建根节点
root = ET.Element("TObject")


# 创建子节点
son1 = ET.Element('son', {'name': 'a1'})
# 创建小儿子
son2 = ET.Element('son', {"name": 'a2'})

# 在子节点中创建子节点
grandson1 = ET.Element('grandson', {'name': 'a11'})
grandson2 = ET.Element('grandson', {'name': 'a12'})
# 将字节点中的子节点追加到子节点中
son1.append(grandson1)
son1.append(grandson2)

root.append(son1)

# 保存
et = ET.ElementTree(root)  # 生成文档对象
et.write("test.xml", encoding="utf-8", xml_declaration=True, short_empty_elements=False)
