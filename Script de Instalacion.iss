;******************************************************************************************************************************************
; [Setup]: Es la parte en donde se configura la aplicación.
;******************************************************************************************************************************************
;[Code]

;procedure delete();
;Var F : String;
;Var J : String;
;begin
;  { Create a file with a line of text in it}
;  Assign (F, '{%USERPROFILE%}/erp.mv.db');
;  Assign (J, '{%USERPROFILE%}/erp.trace.db');
;  { Now remove the file }
;  Erase (f);
;  Erase (J);
;end; 

[Setup]
AppName=ERP
AppVerName=ERP
DefaultGroupName=ERP
AppPublisher=ERP
AppVersion=1.2
AllowNoIcons=false
AppCopyright=
PrivilegesRequired=admin

; Este es el nombre del archivo exe que se va a generar
OutputBaseFilename=ERPSetup

; Este es el nombre de la carpeta en la cual se guardarán los archivos para el programa
; (Por lo general es el mismo nombre de la aplicación)
DefaultDirName={pf}\ERP

;******************************************************************************************************************************************


;******************************************************************************************************************************************
; [Languages] y [Tasks]: No tocar o modificar las siguientes líneas
;******************************************************************************************************************************************
; [Languages] = Es el lenguaje por defecto
; [Tasks]     = Es la indicación para crear los íconos necesarios para iniciar el programa y para desinstalarlo
[Languages]
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Tasks]
Name: desktopicon; Description: Create a &desktop icon; GroupDescription: Additional icons:

;******************************************************************************************************************************************


;******************************************************************************************************************************************
; [Files]: Son los archivos que utilizaremos para crear el instalador
;******************************************************************************************************************************************
[Files]

; Nota: Los parámetros: Tienen que ir tal y como aparecen acá, solo cambiar las rutas C:\ en donde se encuentran los archivos
;       Otra cosa: {sys} = carpeta system de windows
;                  {win} = carpeta windows de windows
;                  {cf}  = carpeta archivos comunes de windows
;                  {tmp} = carpeta temporal de windows
;                  {app} = carpeta donde se va a instalar el programa (fue definida arriba en el parámetro: DefaultDirName= )
; -----------------------------------------------------------------------------------------------------------------------------------------
; Aquí van los archivos de la aplicación: El .exe y otros que ocupe el programa la aplicación
;Source: ‪C:\Program Files (x86)\H2; DestDir: {app}; Flags: ignoreversion
Source: C:\ERParchivos\UltimoIntentoConInno\Crear exe a partir de jar\ERP.exe; DestDir: {app}; Flags: ignoreversion
Source: C:\ERParchivos\UltimoIntentoConInno\Crear exe a partir de jar\*; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: C:\ERParchivos\UltimoIntentoConInno\Crear instalador\Complementos\jdk-8u241-windows-x64.exe; DestDir: "{app}"; Flags: ignoreversion
;Source: C:\ERParchivos\UltimoIntentoConInno\Crear instalador\Complementos\xampp-portable-win32-7.3.2-0-VC15-installer.exe; DestDir: "{app}"; Flags: ignoreversion
;Source: C:\ERParchivos\UltimoIntentoConInno\Crear instalador\Complementos\mypymeserpdatabase.sql; DestDir: {app}; Flags : ignoreversion 
;Source: C:\ERParchivos\UltimoIntentoConInno\Crear instalador\Complementos\mysql-5.5.16-win32.msi; DestDir: "{app}"; Flags: ignoreversion
;Source: C:\ERParchivos\UltimoIntentoConInno\Crear instalador\Complementos\NDP452-KB2901907-x86-x64-AllOS-ENU.exe; DestDir: "{app}"; Flags: ignoreversion
;Source: C:\ERParchivos\UltimoIntentoConInno\Crear instalador\Complementos\Windows6.1-KB2999226-x86.msu;  DestDir: "{app}"; Flags: ignoreversion
;Source: C:\ERParchivos\UltimoIntentoConInno\Crear instalador\Complementos\loadDB.bat; DestDir: "{app}"; Flags: ignoreversion
;******************************************************************************************************************************************


; Omitir esta linea [INI] - No tocar o modificar la siguiente línea
[INI]


;******************************************************************************************************************************************
; [Icons]: Estos son los íconos que el instalador creara en el grupo de programas del sistema de Windows
;******************************************************************************************************************************************
[Icons]

; Nota: Aquí se incluye: El ícono para abrir el programa
;                        El ícono para desinstalar el programa
;                        El ícono que se ubica en el escritorio
; -----------------------------------------------------------------------------------------------------------------------------------------
; {group} = nombre del grupo de programa que se definió arriba en el parámetro: DefaultGroupName=
;           (Por lo general es el mismo nombre de la aplicación)
Name: {group}\ERP; Filename: {app}\ERP.exe; WorkingDir: {app}; IconIndex: 0
Name: {group}\Desinstalar ERP; Filename: {uninstallexe}
Name: {userdesktop}\ERP; Filename: {app}\ERP.exe; Tasks: desktopicon; WorkingDir: {app}; IconIndex: 0
                                         
;******************************************************************************************************************************************


;******************************************************************************************************************************************
; [Run]: Estos son los programas que se instalar como complementos de nuestra aplicación
;******************************************************************************************************************************************
[Run]
; Nota: Esto se ejecuta al momento de la instalación de nuestro programa

; Instalamos MySQL
; (Aquí tenemos que poner el nombre de nuestro motor de base de datos que se encuentra en nuestra carpeta complementos)
;Filename: "{app}\xampp-portable-win32-7.3.2-0-VC15-installer.exe"; Description: "{cm:LaunchProgram,xampp-windows-x64-7.1.32-0-VC14-installer}"; WorkingDir: {app};
;Filename: "{app}\NDP452-KB2901907-x86-x64-AllOS-ENU.exe"; Description: "{cm:LaunchProgram,NDP452-KB2901907-x86-x64-AllOS-ENU.exe}"; WorkingDir: {app};
;Filename: "{app}\Windows6.1-KB2999226-x86"; Description: "{cm:LaunchProgram,Windows6.1-KB2999226-x64.msu}"; WorkingDir: {app};
;Filename: "{app}\mysql\bin\mysqld.exe"; Parameters: "--install"; WorkingDir: "{app}\mysql\bin";  StatusMsg: "Installing the MySQL";  Description: "Installing MySQL";  Flags: runhidden; Check: MySQL_Is;
;Filename: msiexec; Parameters: "/i ""{app}\mysql-5.5.16-win32.msi"" /qn INSTALLDIR=""{app}\mysql"" DATADIR=""{app}\mysql\data"" "; WorkingDir:{app}; StatusMsg: Please wait while we install;  Flags: runhidden
;Filename: "{app}\mysql\bin\MySQLInstanceConfig.exe"; Parameters:"-i -q ""-l{app}\mysql\mysql_install_log.txt"" ""-nMySQL Server 5.5"" ""-p{app}\mysql"" -v5.5.23 ""-t{app}\mysql\my-template.ini"" ""-c{app}\mysql\mysql.ini"" ServerType=SERVER DatabaseType=MIXED Port=3311 StrictMode=yes ConnectionCount=15 Charset=utf8 ServiceName=MySQL55 AddBinToPath=yes RootPassword= SkipNetworking=no"; WorkingDir: {app}; StatusMsg: Configuring MySQL services; Description: Configuring MySQL Service; Flags: runhidden
;Filename: "{app}\mysql\bin\mysql.exe"; Parameters: "-u root -p -h localhost -e ""source {app}\{code:GetScriptData}"""; WorkingDir: {app}; StatusMsg: Loading Database Initial Data; Flags: runhidden waituntilterminated;
;Filename: "{%USERPROFILE%}\erp.mv.db"; 
;Filename: "{%USERPROFILE%}\erp.trace.db"; Flags: deletebeforeinstall;
Filename: "{app}\jdk-8u241-windows-x64.exe"; Description: "{cm:LaunchProgram,jdk-8u241-windows-x64.exe}"; WorkingDir: {app};
Filename: "{app}\ERP.exe"; Description: {cm:LaunchProgram,ERP.exe}; Flags: nowait postinstall skipifsilent
;Filename: "{app}\mysql-installer-community-8.0.18.0.msi"; Description: "{cm:LaunchProgram,mysql-installer-community-8.0.18.0.msi}"; WorkingDir: {app};
;lename: msiexec; Parameters: "/i mysql-installer-community-8.0.18.0.msi /qn INSTALLDIR=""C:\mysql"""; WorkingDir:{app}; StatusMsg: Please wait while we install Mysql 8.0.18.0;  Flags: runhidden
; Esto nos permite crear la base de datos                                          
; (Aquí ponemos el nombre de nuestra base de datos y cambiamos nuestra ruta  C:\ si es necesario)
;Filename: ‪C:\xampp\mysql\bin\mysql; Parameters: "-u root -h localhost -e ""create database mypymeserpdatabase CHARACTER SET 'utf8' COLLATE utf8_spanish_ci";  WorkingDir: {tmp}; StatusMsg: Creando la Base dedatos; Flags: runhidden
;Filename: "{app}\loadDB.bat"; WorkingDir: {app};
; Cargamos la base de datos
; (Acedemos a nuestra base de datos, cargamos nuestras tablas coon nuestro arcchivo [.sql] y cambiamos nuestra ruta  C:\ si es necesario)
;Filename: ‪C:\xampp\mysql\bin\mysql; Parameters: "-u root -h localhost -e ""use mypymeserpdatabase; source mypymeserpdatabase.sql;";  WorkingDir: {tmp}; StatusMsg: Creando Base de Datos; Flags: runhidden

;******************************************************************************************************************************************
;[UninstallRun]
;Filename: {sys}\net.exe; Parameters: "stop ""MySQL55"""; StatusMsg: "Stopping MySQL Service ..."; Flags: runhidden;
;Filename: "{app}\mysql\bin\mysqld.exe"; Parameters: "remove ""MySQL55"""; StatusMsg: "Deleting MySQL Service  ..."; Flags: runhidden;
;Filename: msiexec; Parameters: "/x ""{app}\mysql-5.5.16-win32.msi"" ";
;******************************************************************************************************************************************
; [Messages]: Estos mensajes simplemente son un override ya que vienen en inglés
;             (Cambia los valores de las etiquetas para que aparezcan con el nombre de tu aplicación)
;******************************************************************************************************************************************
;[Code]
;function GetScriptData(Value: string): string;
;begin
;  Result := ExpandConstant('{app}') +'/mypymeserpdatabase.sql';
;  StringChangeEx(Result, '\', '/', True);
;end;

[InstallDelete]
Type: files; Name: {%HOMEPATH}\erp.mv.db
Type: files; Name: {%HOMEPATH}\erp.trace.db

[Messages]

; Este es el título que se mostrara al momento de iniciar el cuadro de dialogo de la instalación (Cambia el Nombre_Aplicación por el nombre de tu aplicacion)
WelcomeLabel1=Instalación de MiPymes PUCMM ERP

; Este es eñ titulo que se mostrara debajo del titulo (Cambia el Nombre_Aplicación por el nombre de tu aplicacion)
WelcomeLabel2=Este proceso instalará ERP.%n%nSe recomienda cerrar todas las aplicaciones abiertas%nantes de continuar.

;******************************************************************************************************************************************