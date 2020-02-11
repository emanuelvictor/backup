package com.fernandomantoan.ponto.android.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fernandomantoan.ponto.android.activity.MainActivity;
import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.entity.Point;
import com.fernandomantoan.ponto.android.repository.PointRepository;
import com.fernandomantoan.ponto.android.support.DatabaseHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

public class Import {

	public static void importFromCSV(String path, Activity activity)
			throws Exception {
		FileInputStream in = new FileInputStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		List<String> id = new LinkedList<String>();
		List<String> date = new LinkedList<String>();
		List<String> obs = new LinkedList<String>();

		String line = "";
		String cvsSplitBy = ",";
		while ((line = reader.readLine()) != null) {
			String[] vet = line.split(cvsSplitBy);
			id.add(vet[0]);
			date.add(vet[1]);
			obs.add(vet[2]);
		}
		StringBuilder builder = new StringBuilder("[");
		for (int i = 1; i < id.size(); i++) {
			if (obs.get(i)==null || obs.get(i)=="null") {
				obs.set(i, "");
			}
			builder = builder.append("{" + "\"" + "obs" + "\"" + ":" + "\"" + obs.get(i) + "\"");
			builder = builder.append(",\"" + "date" + "\"" + ":" + "\"" + date.get(i) + "\"");
			builder = builder.append(",\"" + "id" + "\"" + ":" + id.get(i) + "}");
			if (i != id.size() - 1) {
				builder = builder.append(",");
			}
		}
		builder = builder.append("]");

		// Manda salvar
		save(builder, in, activity);

	}

	public static void importFromJSON(String path, Activity activity)
			throws Exception {
		FileInputStream in = new FileInputStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder builder = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			builder.append(line + "\n");
		}

		// Manda salvar
		save(builder, in, activity);

	}

	private static void save(StringBuilder builder, FileInputStream in,
			Activity actvitity) throws Exception {
		PointRepository repository = new PointRepository(new DatabaseHelper(
				actvitity));
		in.close();
		Log.d("Builder", builder.toString());
		List<Point> points = deserializePoints(builder.toString());
		repository.updateAll(points);
	}

	@SuppressLint("SimpleDateFormat")
	private static List<Point> deserializePoints(String json)
			throws ParseException, JSONException {

		List<Point> points = new ArrayList<Point>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		JSONArray jsonArray = new JSONArray(json);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);

			Point point = new Point(Calendar.getInstance());
			point.setId(obj.getLong("id"));// = obj.getLong("id");
			point.setDate(new GregorianCalendar());// = new GregorianCalendar();
			point.getDate().setTime(dateFormat.parse(obj.getString("date")));
			point.setObs(obj.getString("obs"));

			points.add(point);
		}
		return points;
	}

	public static void onFileSelected(final Activity activity, final File file) {

		AlertDialog alertDialog = new AlertDialog.Builder(activity)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Importação")
				.setMessage(
						"Esta operação irá apagar os dados atuais. Deseja realmente importar os dados do arquivo '"
								+ file.getName() + "'?")
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								try {
									String path = file.getAbsolutePath();
									if (path.contains(".json")) {
										importFromJSON(path,
												(MainActivity) activity);
										Toast.makeText(
												activity,
												"Importação efetuada com sucesso.",
												Toast.LENGTH_SHORT).show();
										((Navigation) activity)
												.setVariables(new PointVariables(
														activity));
										((Navigation) activity)
												.goTo(((Navigation) activity)
														.getVariables()
														.getFragment());
									} else if (path.contains(".csv")) {
										importFromCSV(path,
												(MainActivity) activity);
										Toast.makeText(
												activity,
												"Importação efetuada com sucesso.",
												Toast.LENGTH_SHORT).show();
										((Navigation) activity)
												.setVariables(new PointVariables(
														activity));
										// Esse getFragment é gâmbia
										((Navigation) activity)
												.goTo(((Navigation) activity)
														.getVariables()
														.getFragment());
									} else {
										Toast.makeText(
												activity,
												"Selecione o arquivo .json ou .csv",
												Toast.LENGTH_LONG).show();
									}
								} catch (Exception e) {
									Toast.makeText(
											activity,
											"Não foi possível efetuar a importação.",
											Toast.LENGTH_SHORT).show();
									e.printStackTrace();
								}
							}
						}).setNegativeButton("Não", null).show();
		alertDialog.show();
	}
}
