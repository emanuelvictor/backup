object Form1: TForm1
  Left = 488
  Top = 249
  Align = alClient
  BorderStyle = bsNone
  Caption = 'Sair ?'
  ClientHeight = 86
  ClientWidth = 274
  Color = clWhite
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnDestroy = btnnaoClick
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 536
    Top = 304
    Width = 205
    Height = 13
    Caption = 'Tem Certeza que deseja fechar o aplicativo'
  end
  object Label2: TLabel
    Left = 568
    Top = 359
    Width = 138
    Height = 13
    Caption = 'Salve todos os seus arquivos'
  end
  object btnsim: TButton
    Left = 544
    Top = 327
    Width = 75
    Height = 25
    Caption = '&Sim'
    TabOrder = 0
    OnClick = btnsimClick
  end
  object btnnao: TButton
    Left = 656
    Top = 327
    Width = 75
    Height = 25
    Caption = '&Nao'
    TabOrder = 1
    OnClick = btnnaoClick
  end
end
