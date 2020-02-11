/**
 * 
 */
package org.springframework.data.domain;

/**
 * @author emanuelvictor
 *
 */
public class PageableImpl implements Pageable
{

	private PageRequest pageRequest;

	/**
	 * 
	 */
	public PageableImpl()
	{
		super();
	}

	/**
	 * @param pageRquest
	 */
	public PageableImpl( PageRequest pageRquest )
	{
		super();
		this.pageRequest = pageRquest;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getPageNumber()
	 */
	@Override
	public int getPageNumber()
	{
		return this.pageRequest != null && ! this.pageRequest.equals( null ) ? this.pageRequest.getPageNumber() : 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getPageSize()
	 */
	@Override
	public int getPageSize()
	{
		return this.pageRequest != null && ! this.pageRequest.equals( null ) ? this.pageRequest.getPageSize() : 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getOffset()
	 */
	@Override
	public int getOffset()
	{
		return this.pageRequest != null && ! this.pageRequest.equals( null ) ? this.pageRequest.getOffset() : 0;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#getSort()
	 */
	@Override
	public Sort getSort()
	{
		return this.pageRequest != null && ! this.pageRequest.equals( null ) ? this.pageRequest.getSort() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#next()
	 */
	@Override
	public Pageable next()
	{
		return this.pageRequest != null && ! this.pageRequest.equals( null ) ? this.pageRequest.next() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#previousOrFirst()
	 */
	@Override
	public Pageable previousOrFirst()
	{
		return this.pageRequest != null && ! this.pageRequest.equals( null ) ? this.pageRequest.previousOrFirst() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#first()
	 */
	@Override
	public Pageable first()
	{
		return this.pageRequest != null && ! this.pageRequest.equals( null ) ? this.pageRequest.first() : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.domain.Pageable#hasPrevious()
	 */
	@Override
	public boolean hasPrevious()
	{
		return this.pageRequest != null && ! this.pageRequest.equals( null ) ? this.pageRequest.hasPrevious() : null;
	}

}
