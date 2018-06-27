package com.yuerui.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Table(name = "sys_user")
public  class SysUser implements Serializable {
	private static final long serialVersionUID = 5922477750792334910L;

	/**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid_short()")
    private Long id;

    /**
     * 所属企业编号
     */
    private Long cid;

    /**
     * 关联的员工编号
     * <li>注意事项1：当staffId为空时,用户登录后需要提示?
     */
    @Column(name = "staff_id")
    private Long staffId;
    
    /**
     * 用户名
     */
    private String username;

    /**
     * 登录加密密码（SHA-1）
     */
    private String password;

    /**
     * 登录盐值
     */
    private String salt;

    /**
     * 头像文件编号（file.id)
     */
    @Column(name = "face_id")
    private Long faceId;

    /**
     * 用户姓名
     */
    private String fullname;

    /**
     * 是否有效（0-无效，1-有效）
     */
    @Column(name = "is_valid")
    private Boolean isValid;

    /**
     * 是否管理员
     */
    @Column(name = "is_manger")
    private Boolean isManger;

    /**
     * 是否禁用
     */
    @Column(name = "is_forbidden")
    private Boolean isForbidden;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 最后修改人ID
     */
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 最后修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    //===================TODO  数据属性===============================
    /**
     * 用户权限列表
     */
    @Transient
    private List<String> permissionList;
    
    /**
     * 用户角色列表
     */
    @Transient
    private List<String> roleList;
    
    /**
     * 数据权限范围(1：全厂，用cid标识；2：部门，与组织架构的code一致；3：个人，0)
     */
    @Transient
    private Integer dataScope;
    /**
     * 用户权限码列表
     */
    @Transient
    private Set<String> permissionCodeList;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Long getFaceId() {
		return faceId;
	}
	public void setFaceId(Long faceId) {
		this.faceId = faceId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public Boolean getIsManger() {
		return isManger;
	}
	public void setIsManger(Boolean isManger) {
		this.isManger = isManger;
	}
	public Boolean getIsForbidden() {
		return isForbidden;
	}
	public void setIsForbidden(Boolean isForbidden) {
		this.isForbidden = isForbidden;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public List<String> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}
	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	public Integer getDataScope() {
		return dataScope;
	}
	public void setDataScope(Integer dataScope) {
		this.dataScope = dataScope;
	}
	public Set<String> getPermissionCodeList() {
		return permissionCodeList;
	}
	public void setPermissionCodeList(Set<String> permissionCodeList) {
		this.permissionCodeList = permissionCodeList;
	}
    
    
    
}