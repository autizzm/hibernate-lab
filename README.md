## Startup:

#### Configuration:

In hibernate.cfg.xml change the following values:

If your credentials are `root`-`root` -> no need to change them. (Mine are too:)

```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration SYSTEM
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <property name="hibernate.dialect">
            org.hibernate.dialect.MySQLDialect
        </property>
        <property name="hibernate.connection.driver_class">
            com.mysql.cj.jdbc.Driver
        </property>

        <property name="hibernate.connection.url">
            jdbc:mysql://localhost:3306/hibernate
        </property>
        <property name="hibernate.connection.username">
            YOUR_USERNAME_HERE (root by default)
        </property>
        <property name="hibernate.connection.password">
            YPUR_PASSWORD_HERE (root by default)
        </property>
        <property name="current_session_context_class">thread</property>
        <property name="show_sql">true</property>

        <mapping resource="Developer.hbm.xml"/>

    </session-factory>
</hibernate-configuration>

```

#### DB initialization:
```bash
mysql -u <your_user_name> -p < <path_to_file_on_your_pc>/initDB.sql
```

How the command looks on mine pc (i'm in the project's directory):
```bash
mysql -u root -p < src/main/resources/initDB.sql
```

---

