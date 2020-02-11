package com.fernandomantoan.ponto.android.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.entity.Point;
import com.fernandomantoan.ponto.android.repository.PointRepository;
import com.fernandomantoan.ponto.android.support.DatabaseHelper;

@SuppressLint("SimpleDateFormat")
public class Export {

	// Export to csv function
	public static void exportToCSV(final Context context) {
		new AlertDialog.Builder(context)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Exportação")
				.setMessage(
						"Deseja realmente exportar os dados? Eles ficarão localizados na pasta \"ponto\" do seu cartão SD.")
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(
									DialogInterface dialogInterface, int i) {
								exportCSV(context);
							}
						}).setNegativeButton("Não", null).show();

	}

	private static void exportCSV(Context context) {
		// Função de exportação
		try {
			Toast.makeText(context, R.string.exporting, Toast.LENGTH_SHORT)
					.show();
			PointRepository repository = new PointRepository(
					new DatabaseHelper(context));
			List<Point> points = repository.findAllByDate(null);

			if (points.size() != 0 && HistoryPath.isExternalStorageWritable()) {

				File path = Environment
						.getExternalStoragePublicDirectory("ponto");
				File file = new File(path, "export.csv");
				path.mkdirs();
				FileOutputStream out = new FileOutputStream(file);
				OutputStreamWriter writer = new OutputStreamWriter(out);
				writer.append("Código");
				writer.append(',');
				writer.append("Data e hora");
				writer.append(',');
				writer.append("Observações");
				writer.append('\n');
				for (int i = points.size() - 1; i >= 0; i--) {
					Point point = new Point(Calendar.getInstance());
					point = points.get(i);
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					writer.append(point.getId().toString());
					writer.append(',');
					writer.append(dateFormat.format(point.getDate().getTime()));
					writer.append(',');
					writer.append(point.getObs());
					writer.append('\n');
				}
				writer.close();
				Toast.makeText(context, "Dados exportados com sucesso.",
						Toast.LENGTH_SHORT).show();
			} else if (points.isEmpty()) {
				Toast.makeText(context,
						"Erro ao exportar, tabela de pontos vazia",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "Erro ao exportar", Toast.LENGTH_SHORT)
						.show();
			}
		} catch (IOException e) {
			Toast.makeText(context, "Erro ao exportar", Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
		}
	}

	// Export to json function
	public static void exportToJSON(final Context context) {
		new AlertDialog.Builder(context)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Exportação")
				.setMessage(
						"Deseja realmente exportar os dados? Eles ficarão localizados na pasta \"ponto\" do seu cartão SD.")
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(
									DialogInterface dialogInterface, int i) {
								exportJSON(context);
							}
						}).setNegativeButton("Não", null).show();
	}

	private static void exportJSON(Context context) {
		// Função de exportação
		Toast.makeText(context, R.string.exporting, Toast.LENGTH_SHORT).show();
		PointRepository repository = new PointRepository(new DatabaseHelper(
				context));
		List<Point> points = repository.findAllByDate(null);

		JSONArray export = new JSONArray();
		try {
			for (int i = points.size() - 1; i >= 0; i--) {
				Point point = new Point(Calendar.getInstance());
				point = points.get(i);
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", point.getId());
				jsonObject.put("date", dateFormat.format(point.getDate().getTime()));
				export.put(jsonObject);
			}
			Log.d("JsonObject", export.toString());
		} catch (JSONException e) {
			Log.e("EXPORT", "Erro ao exportar os dados", e);
		}

		if (export.length() > 0 && HistoryPath.isExternalStorageWritable()) {
			File path = Environment.getExternalStoragePublicDirectory("ponto");
			File file = new File(path, "export.json");

			try {
				path.mkdirs();
				FileOutputStream out = new FileOutputStream(file);
				OutputStreamWriter writer = new OutputStreamWriter(out);
				writer.write(export.toString());
				writer.close();
				Toast.makeText(context, "Dados exportados com sucesso.",
						Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				Log.e("EXPORT", "Erro ao exportar o ponto", e);
			}
		} else {
			Toast.makeText(context, "Erro ao exportar", Toast.LENGTH_SHORT)
					.show();
		}
	}

}
