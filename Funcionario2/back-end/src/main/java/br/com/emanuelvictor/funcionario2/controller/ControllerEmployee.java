package br.com.emanuelvictor.funcionario2.controller;

import br.com.emanuelvictor.funcionario2.entity.user.Employee;
import br.com.emanuelvictor.funcionario2.service.ServiceEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerEmployee
{

	@Autowired
	ServiceEmployee serviceEmployee;

	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public @ResponseBody Employee postEmployee( @RequestBody Employee employee)
	{
		return this.serviceEmployee.save(employee);
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployees()
	{
		return this.serviceEmployee.find();
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee( @PathVariable("id") Integer id )
	{
		return this.serviceEmployee.find( id );
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee( @PathVariable("id") Integer id )
	{
		this.serviceEmployee.delete( id );
	}

}
