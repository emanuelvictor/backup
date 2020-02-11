package com.fernandomantoan.ponto.android.fragment.tablet;

import java.util.Calendar;
import java.util.List;

import android.view.MenuItem;
import android.widget.Toast;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.entity.Point;
import com.fernandomantoan.ponto.android.fragment.EditPointFragment;
import com.fernandomantoan.ponto.android.repository.PointRepository;
import com.fernandomantoan.ponto.android.support.DatabaseHelper;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

public class EditPointTablet extends EditPointFragment {

	@Override
	public void handle(Object obj) throws Exception {
		if (obj instanceof MenuItem) {
			switch (((MenuItem) obj).getItemId()) {
			case R.id.action_add:
				try {
					if (this.calendar == null) {
						this.calendar = Calendar.getInstance();
					}
					PointRepository repository = new PointRepository(
							new DatabaseHelper(getActivity()));
					List<Point> points = repository
							.findAllByDate(this.calendar);
					if (points != null && points.size() >= 4) {
						Toast.makeText(getActivity(), R.string.more_than_four,
								Toast.LENGTH_SHORT).show();
					} else {
						Calendar c = this.calendar;
						c.set(Calendar.HOUR_OF_DAY,
								Calendar.getInstance()
										.get(Calendar.HOUR_OF_DAY));
						c.set(Calendar.MINUTE,
								Calendar.getInstance().get(Calendar.MINUTE));
						Point p = new Point(Calendar.getInstance());
						p.setDate(c);
						repository.save(p);
						((InterfaceFragment) getActivity()).update();
						Toast.makeText(getActivity(), R.string.saved,
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					Toast.makeText(getActivity(), R.string.save_error,
							Toast.LENGTH_SHORT).show();
				}
				break;
			case android.R.id.home:
				this.home();
				break;
			default:
				super.handle(obj);
			}
			
			//TODO isto aqui vai pra classe mãe
		} else super.handle(obj);
	}

	@Override
	public void back() throws Exception {
		if (((PointVariables) ((Navigation) getActivity()).getVariables())
				.getHistoryNavigation().isHome()) {
			this.getActivity().finish();
		} else {
			((PointVariables) ((Navigation) getActivity()).getVariables())
					.getHistoryNavigation().decrementHistory();
			((InterfaceFragment) getActivity()).update();
		}
	}

	@Override
	public void configureActionBar() {
		
	}

	@Override
	public void home() throws Exception {
			this.back();
	}

	@Override
	public void goTo(Object obj) throws Exception {

	}
}
