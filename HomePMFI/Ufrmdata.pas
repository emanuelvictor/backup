unit Ufrmdata;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, ComCtrls, jpeg, StdCtrls, Menus;

type
  TForm2 = class(TForm)
    Label1: TLabel;
    Label2: TLabel;
    MonthCalendar1: TMonthCalendar;
    Timer1: TTimer;
    Fechar: TImage;
    procedure FecharClick(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form2: TForm2;

implementation

{$R *.dfm}

procedure TForm2.FecharClick(Sender: TObject);
begin
  CLOSE;
end;

procedure TForm2.Timer1Timer(Sender: TObject);
begin
  label1.Caption := TimeToStr(now);
end;

end.
