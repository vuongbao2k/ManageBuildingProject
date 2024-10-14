package com.javaweb.builder;

public class CustomerSearchBuilder {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private Long staffId;

    public CustomerSearchBuilder(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.staffId = builder.staffId;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getStaffId() {
        return staffId;
    }

    public static class Builder {
        private Long id;
        private String fullName;
        private String phone;
        private String email;
        private Long staffId;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public CustomerSearchBuilder build() {return new CustomerSearchBuilder(this);}
    }
}
