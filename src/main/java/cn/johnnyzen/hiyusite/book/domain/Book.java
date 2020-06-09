package cn.johnnyzen.hiyusite.book.domain;

/**
 * @orgnization: 成都四方伟业软件股份有限公司
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  01:09:19
 * @description: ...
 */

public class Book {
    private Integer id;

    private String name;

    /**
     * 书籍所属分类
     */
    private String className;

    /**
     * 简介/简述
     */
    private String profile;

    private String authors;

    private String recommenders;

    private String importantLevel;

    private String resource;

    private String resourceType;

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getRecommenders() {
        return recommenders;
    }

    public void setRecommenders(String recommenders) {
        this.recommenders = recommenders;
    }

    public String getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(String importantLevel) {
        this.importantLevel = importantLevel;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", profile='" + profile + '\'' +
                ", authors='" + authors + '\'' +
                ", recommenders='" + recommenders + '\'' +
                ", importantLevel='" + importantLevel + '\'' +
                ", resource='" + resource + '\'' +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }
}
