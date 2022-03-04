# 打开cmd命令行窗口，输入javac或javap等指令，正常显示，但是IDEA的Teminal显示错误
先检查IDEA中配置的jdk是否是你环境变量配置的对应版本的jdk
查看winsows 环境变量中ComSpec的变量值 C:\Windows\System32\cmd.exe。
不要用%SystemRoot%\System32\cmd.exe这种配置值，IDEA无法识别到。
