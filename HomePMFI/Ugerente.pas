unit Ugerente;

interface

uses
  Shellapi, ufrmdata, usair, Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, Menus, ExtCtrls, jpeg, StdCtrls, Buttons, AppEvnts, ComCtrls,
  HTTPApp, HTTPProd;

type
  TUfrmgerente = class(TForm)
    Logo: TImage;
    Excel: TImage;
    Word: TImage;
    Blocodenotas: TImage;
    Calculadora: TImage;
    Fechar: TImage;
    Dataehora: TImage;
    pmfi: TImage;
    lb: TLabel;

    procedure Calculadora1Click(Sender: TObject);
    procedure BlocodeNotas1Click(Sender: TObject);
    procedure InternetExplorer1Click(Sender: TObject);
    procedure Excel1Click(Sender: TObject);
    procedure Word1Click(Sender: TObject);
    procedure FecharClick(Sender: TObject);
    procedure FormCloseQuery(Sender: TObject; var CanClose: Boolean);
    procedure Timer1Timer(Sender: TObject);
    procedure DataehoraClick(Sender: TObject);
    procedure pmfiClick(Sender: TObject);
  
  private
   //FPodeFechar: boolean;  { Private declarations }
  public
  //function iniciafalse : boolean;
  SAIR : TFORM1;
   data : TForm2;

    { Public declarations }
  end;

var
  Ufrmgerente: TUfrmgerente;

implementation

{$R *.dfm}


procedure TUfrmgerente.Calculadora1Click(Sender: TObject);
begin
   winexec('calc.exe',SW_SHOW);
end;

procedure TUfrmgerente.BlocodeNotas1Click(Sender: TObject);
begin
 winexec('notepad.exe',sw_show);
end;

procedure TUfrmgerente.InternetExplorer1Click(Sender: TObject);
//var   edArquivo : string;
begin
  //winexec('C:\Arquivos de programas\Internet Explorer\iexplore.exe',sw_showmaximized);
  //winexec('C:\Program Files\Internet Explorer\iexplore.exe',sw_showmaximized);
 // winexec('c:\windows\iexplore.exe',sw_showmaximized);
  //winexec('iexplore.bat',sw_showminimized);


 // edArquivo := 'c:\arquivos de programas\internet explorer\iexplore.exe';
 // ShellExecute(handle,'open',PChar(edArquivo), '','',SW_SHOWNORMAL);
 shellexecute (handle, 'open', 'iexplore.exe', '', nil, sw_shownormal);
end;

procedure TUfrmgerente.Excel1Click(Sender: TObject);
begin
 // winexec('c:\arquivos de programas\Microsoft Office\Office11\excel.exe',sw_showmaximized);
 // winexec('excel.bat',sw_showminimized);
 shellexecute (handle, 'open', 'excel.exe','', nil, sw_shownormal);
end;

procedure TUfrmgerente.Word1Click(Sender: TObject);
begin
 shellexecute (handle, 'open', 'winword.exe', '', nil, sw_shownormal);
 // winexec('word.bat',sw_showmaximized);
 // winexec('c:\arquivos de programas\Microsoft Office\Office11\winword.exe',sw_showmaximized);
 // winexec('winword.exe',sw_showmaximized);
end;





procedure TUfrmgerente.FecharClick(Sender: TObject);
begin
  close;
  //winexec('logoff.bat',sw_showminimized);
end;

procedure TUfrmgerente.FormCloseQuery(Sender: TObject;
  var CanClose: Boolean);
begin
//winexec('logoff.bat',sw_showminimized);
end;


procedure TUfrmgerente.Timer1Timer(Sender: TObject);
begin
  //Label1.Caption := TimeToStr(now);
end;

procedure TUfrmgerente.DataehoraClick(Sender: TObject);
begin

 data := TForm2.Create(nil);
 data.ShowModal;
end;

procedure TUfrmgerente.pmfiClick(Sender: TObject);
begin
    shellexecute (handle, 'open', 'iexplore.exe', '', nil, sw_shownormal);
end;

end .
