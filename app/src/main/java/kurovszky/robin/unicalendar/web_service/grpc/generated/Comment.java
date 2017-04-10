// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: handler.proto

package kurovszky.robin.unicalendar.web_service.grpc.generated;

/**
 * Protobuf type {@code Comment}
 */
public final class Comment extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:Comment)
    CommentOrBuilder {
  // Use Comment.newBuilder() to construct.
  private Comment(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
    this.unknownFields = builder.getUnknownFields();
  }
  private Comment(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

  private static final Comment defaultInstance;
  public static Comment getDefaultInstance() {
    return defaultInstance;
  }

  public Comment getDefaultInstanceForType() {
    return defaultInstance;
  }

  private final com.google.protobuf.UnknownFieldSet unknownFields;
  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
      getUnknownFields() {
    return this.unknownFields;
  }
  private Comment(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    initFields();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {
            bitField0_ |= 0x00000001;
            id_ = input.readUInt64();
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            commentText_ = bs;
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            userId_ = input.readUInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            subjectId_ = input.readUInt64();
            break;
          }
          case 42: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000010;
            userName_ = bs;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e.getMessage()).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return Handler.internal_static_Comment_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return Handler.internal_static_Comment_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            Comment.class, Builder.class);
  }

  public static com.google.protobuf.Parser<Comment> PARSER =
      new com.google.protobuf.AbstractParser<Comment>() {
    public Comment parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Comment(input, extensionRegistry);
    }
  };

  @java.lang.Override
  public com.google.protobuf.Parser<Comment> getParserForType() {
    return PARSER;
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>required uint64 id = 1;</code>
   */
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required uint64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int COMMENTTEXT_FIELD_NUMBER = 2;
  private java.lang.Object commentText_;
  /**
   * <code>required string commentText = 2;</code>
   */
  public boolean hasCommentText() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string commentText = 2;</code>
   */
  public java.lang.String getCommentText() {
    java.lang.Object ref = commentText_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        commentText_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string commentText = 2;</code>
   */
  public com.google.protobuf.ByteString
      getCommentTextBytes() {
    java.lang.Object ref = commentText_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      commentText_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USERID_FIELD_NUMBER = 3;
  private long userId_;
  /**
   * <code>required uint64 userId = 3;</code>
   */
  public boolean hasUserId() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required uint64 userId = 3;</code>
   */
  public long getUserId() {
    return userId_;
  }

  public static final int SUBJECTID_FIELD_NUMBER = 4;
  private long subjectId_;
  /**
   * <code>required uint64 subjectId = 4;</code>
   */
  public boolean hasSubjectId() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>required uint64 subjectId = 4;</code>
   */
  public long getSubjectId() {
    return subjectId_;
  }

  public static final int USERNAME_FIELD_NUMBER = 5;
  private java.lang.Object userName_;
  /**
   * <code>optional string userName = 5;</code>
   */
  public boolean hasUserName() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>optional string userName = 5;</code>
   */
  public java.lang.String getUserName() {
    java.lang.Object ref = userName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        userName_ = s;
      }
      return s;
    }
  }
  /**
   * <code>optional string userName = 5;</code>
   */
  public com.google.protobuf.ByteString
      getUserNameBytes() {
    java.lang.Object ref = userName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      userName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private void initFields() {
    id_ = 0L;
    commentText_ = "";
    userId_ = 0L;
    subjectId_ = 0L;
    userName_ = "";
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCommentText()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasUserId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSubjectId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeUInt64(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeBytes(2, getCommentTextBytes());
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeUInt64(3, userId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeUInt64(4, subjectId_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeBytes(5, getUserNameBytes());
    }
    getUnknownFields().writeTo(output);
  }

  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, getCommentTextBytes());
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, userId_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, subjectId_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(5, getUserNameBytes());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  protected java.lang.Object writeReplace()
      throws java.io.ObjectStreamException {
    return super.writeReplace();
  }

  public static Comment parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Comment parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Comment parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Comment parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Comment parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static Comment parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static Comment parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static Comment parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static Comment parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static Comment parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(Comment prototype) {
    return newBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() { return newBuilder(this); }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code Comment}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Comment)
          CommentOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Handler.internal_static_Comment_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Handler.internal_static_Comment_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Comment.class, Builder.class);
    }

    // Construct using grpc.generated.Comment.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    private static Builder create() {
      return new Builder();
    }

    public Builder clear() {
      super.clear();
      id_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      commentText_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      userId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      subjectId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      userName_ = "";
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return Handler.internal_static_Comment_descriptor;
    }

    public Comment getDefaultInstanceForType() {
      return Comment.getDefaultInstance();
    }

    public Comment build() {
      Comment result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public Comment buildPartial() {
      Comment result = new Comment(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.commentText_ = commentText_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.userId_ = userId_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.subjectId_ = subjectId_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.userName_ = userName_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Comment) {
        return mergeFrom((Comment)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Comment other) {
      if (other == Comment.getDefaultInstance()) return this;
      if (other.hasId()) {
        setId(other.getId());
      }
      if (other.hasCommentText()) {
        bitField0_ |= 0x00000002;
        commentText_ = other.commentText_;
        onChanged();
      }
      if (other.hasUserId()) {
        setUserId(other.getUserId());
      }
      if (other.hasSubjectId()) {
        setSubjectId(other.getSubjectId());
      }
      if (other.hasUserName()) {
        bitField0_ |= 0x00000010;
        userName_ = other.userName_;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }

    public final boolean isInitialized() {
      if (!hasId()) {
        
        return false;
      }
      if (!hasCommentText()) {
        
        return false;
      }
      if (!hasUserId()) {
        
        return false;
      }
      if (!hasSubjectId()) {
        
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Comment parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (Comment) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long id_ ;
    /**
     * <code>required uint64 id = 1;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required uint64 id = 1;</code>
     */
    public long getId() {
      return id_;
    }
    /**
     * <code>required uint64 id = 1;</code>
     */
    public Builder setId(long value) {
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required uint64 id = 1;</code>
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object commentText_ = "";
    /**
     * <code>required string commentText = 2;</code>
     */
    public boolean hasCommentText() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string commentText = 2;</code>
     */
    public java.lang.String getCommentText() {
      java.lang.Object ref = commentText_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          commentText_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string commentText = 2;</code>
     */
    public com.google.protobuf.ByteString
        getCommentTextBytes() {
      java.lang.Object ref = commentText_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        commentText_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string commentText = 2;</code>
     */
    public Builder setCommentText(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      commentText_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string commentText = 2;</code>
     */
    public Builder clearCommentText() {
      bitField0_ = (bitField0_ & ~0x00000002);
      commentText_ = getDefaultInstance().getCommentText();
      onChanged();
      return this;
    }
    /**
     * <code>required string commentText = 2;</code>
     */
    public Builder setCommentTextBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      commentText_ = value;
      onChanged();
      return this;
    }

    private long userId_ ;
    /**
     * <code>required uint64 userId = 3;</code>
     */
    public boolean hasUserId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required uint64 userId = 3;</code>
     */
    public long getUserId() {
      return userId_;
    }
    /**
     * <code>required uint64 userId = 3;</code>
     */
    public Builder setUserId(long value) {
      bitField0_ |= 0x00000004;
      userId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required uint64 userId = 3;</code>
     */
    public Builder clearUserId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      userId_ = 0L;
      onChanged();
      return this;
    }

    private long subjectId_ ;
    /**
     * <code>required uint64 subjectId = 4;</code>
     */
    public boolean hasSubjectId() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required uint64 subjectId = 4;</code>
     */
    public long getSubjectId() {
      return subjectId_;
    }
    /**
     * <code>required uint64 subjectId = 4;</code>
     */
    public Builder setSubjectId(long value) {
      bitField0_ |= 0x00000008;
      subjectId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required uint64 subjectId = 4;</code>
     */
    public Builder clearSubjectId() {
      bitField0_ = (bitField0_ & ~0x00000008);
      subjectId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object userName_ = "";
    /**
     * <code>optional string userName = 5;</code>
     */
    public boolean hasUserName() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional string userName = 5;</code>
     */
    public java.lang.String getUserName() {
      java.lang.Object ref = userName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          userName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string userName = 5;</code>
     */
    public com.google.protobuf.ByteString
        getUserNameBytes() {
      java.lang.Object ref = userName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string userName = 5;</code>
     */
    public Builder setUserName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      userName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string userName = 5;</code>
     */
    public Builder clearUserName() {
      bitField0_ = (bitField0_ & ~0x00000010);
      userName_ = getDefaultInstance().getUserName();
      onChanged();
      return this;
    }
    /**
     * <code>optional string userName = 5;</code>
     */
    public Builder setUserNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      userName_ = value;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:Comment)
  }

  static {
    defaultInstance = new Comment(true);
    defaultInstance.initFields();
  }

  // @@protoc_insertion_point(class_scope:Comment)
}

