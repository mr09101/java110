package bitcamp.java110.cms.dao.impl;

public class DuplicationDaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicationDaoException() {
        super();
    }

    public DuplicationDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicationDaoException(String message) {
        super(message);
    }

}
