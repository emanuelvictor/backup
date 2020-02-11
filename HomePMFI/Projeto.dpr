program Projeto;

uses
  Forms,
  Ugerente in 'Ugerente.pas' {Ufrmgerente},
  Usair in 'Usair.pas' {Form1},
  Udataehora in 'Udataehora.pas' {Form3},
  Ufrmdata in 'Ufrmdata.pas' {Form2},
  Uframe in 'Uframe.pas' {Frame1: TFrame};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TUfrmgerente, Ufrmgerente);
  Application.CreateForm(TForm1, Form1);
  Application.CreateForm(TForm2, Form2);
  Application.Run;
  

end.
