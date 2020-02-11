unit Usair;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls;

type
  TForm1 = class(TForm)
    Label1: TLabel;
    btnsim: TButton;
    btnnao: TButton;
    Label2: TLabel;
    function sairounao:bool;
    procedure btnsimClick(Sender: TObject);
    procedure btnnaoClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
   resp : char;
implementation

{$R *.dfm}

procedure TForm1.btnsimClick(Sender: TObject);
begin
   resp := 's';
   close;
end;

procedure TForm1.btnnaoClick(Sender: TObject);
begin
   resp := 'n';
   close;
end;
function tform1.sairounao: bool;
begin
  if(resp = 'n')then
    result := false;
  if(resp = 's')then
    result := true;
end;




end.
